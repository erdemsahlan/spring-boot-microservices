package com.homeproject.homeprojectfortrainingspringframework.services;

import com.homeproject.homeprojectfortrainingspringframework.dtos.DepartmentDto;
import com.homeproject.homeprojectfortrainingspringframework.dtos.EmployeeDto;
import com.homeproject.homeprojectfortrainingspringframework.dtos.MoneyTransferRequestDto;
import com.homeproject.homeprojectfortrainingspringframework.modals.Department;
import com.homeproject.homeprojectfortrainingspringframework.modals.Employee;
import com.homeproject.homeprojectfortrainingspringframework.modals.Logs;
import com.homeproject.homeprojectfortrainingspringframework.outsourceModals.TicketModal;
import com.homeproject.homeprojectfortrainingspringframework.repository.IEmployeeRepository;
import com.homeproject.homeprojectfortrainingspringframework.repository.IGenericJpaRepository;
import com.homeproject.homeprojectfortrainingspringframework.repository.ILogRepository;
import com.homeproject.homeprojectfortrainingspringframework.utilities.Mapper;
import lombok.extern.java.Log;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    private final IEmployeeRepository _employeeRepository;
    private final DepartmentService _departmentService;
    private final WebClient _webClient;
    private final AmqpTemplate _rabbitTemplate;
    private final DirectExchange exchange;
    private final ILogRepository _logRepository;


    @Value("${sample.rabbitmq.firstRoutingKey}")
    String firstRoutingKey;
    @Value("${sample.rabbitmq.secondRoutingKey}")
    String secondRoutingKey;
    @Value("${sample.rabbitmq.thirdRoutingKey}")
    String thirdRoutingKey;



    public EmployeeService(IEmployeeRepository employeeRepository, DepartmentService departmentService, WebClient webClient, AmqpTemplate rabbitTemplate, DirectExchange exchange, ILogRepository logRepository) {
        _employeeRepository = employeeRepository;
        _departmentService = departmentService;
        _webClient = webClient;
        _rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        _logRepository = logRepository;
    }

    public ResponseEntity<List<EmployeeDto>> GetALlEmployee()
    {
        List<Employee> employee = _employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList=Mapper.mapAll(employee, EmployeeDto.class);
        return  ResponseEntity.status(HttpStatus.OK).body(employeeDtoList);
    }

    public ResponseEntity<Boolean> CreateEmployee(EmployeeDto employeeDto)
    {
        Employee employee=Mapper.map(employeeDto, Employee.class);

        var result=_employeeRepository.save(employee);
        if (result!=null)
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    public  ResponseEntity<EmployeeDto> GetEmployeeById(int id) {
        Employee employee=_employeeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("{id}"));
        EmployeeDto employeeDto=Mapper.map(employee,EmployeeDto.class);
        return  ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    public ResponseEntity<?> UpdateEmployee(int id, @RequestBody EmployeeDto employeeDto)
    {
        Employee employee = _employeeRepository.findById(id).orElseThrow();
        Department department = _departmentService.FindDepartmentByIdForOtherServices(employeeDto.getDepartmentId());
        Employee employeeUpdateData=Mapper.map(employeeDto,Employee.class);
        employee.setName(employeeUpdateData.getName());
        employee.setSurname(employeeUpdateData.getSurname());
        employee.setCountry(employeeUpdateData.getCountry());
        employee.setPhone(employeeUpdateData.getPhone());
        employee.setEmail(employeeUpdateData.getEmail());
        employee.setAddress(employeeUpdateData.getAddress());
        employee.setDepartment(department);
        _employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    public ResponseEntity<?> DeleteEmployee(int id)
    {
        Employee employee=_employeeRepository.findById(id).orElseThrow();
        employee.setActive(false);
        _employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    public ResponseEntity<?> GetEmployeeByTcNo(String tcNo)
    {
      Employee employee = _employeeRepository.findEmployeeByTcNo(tcNo).orElseThrow();
      EmployeeDto employeeDto = Mapper.map(employee,EmployeeDto.class);
      return  ResponseEntity.status(HttpStatus.OK).body(employeeDto);
    }

    public ResponseEntity<?> GetEmployeeTicketInfo(int id)
    {
        EmployeeDto employeeDto = GetEmployeeById(id).getBody();
        TicketModal ticketModal = _webClient.get()
                .uri("http://localhost:8081/ticket/find/tcNo/"+employeeDto.getEmployeeIdentityNo())
                .retrieve().bodyToMono(TicketModal.class).block();
        return ResponseEntity.status(HttpStatus.OK).body(ticketModal);
    }

    public void transferMoney(@RequestBody MoneyTransferRequestDto moneyTransferRequestDto)
    {
        _rabbitTemplate.convertAndSend(exchange.getName(),firstRoutingKey,moneyTransferRequestDto);
    }

    @RabbitListener(queues = "${sample.rabbitmq.firstQueue}")
    public  void checkSenderAccount(MoneyTransferRequestDto moneyTransferRequestDto)
    {
        Optional<Employee> employeeSender = _employeeRepository.findById(moneyTransferRequestDto.getSenderId());
        employeeSender.ifPresentOrElse(sender-> {
            if (sender.getTotalCash() > moneyTransferRequestDto.getAmount()) {
                sender.setTotalCash(sender.getTotalCash() - moneyTransferRequestDto.getAmount());
                _employeeRepository.save(sender);
                _rabbitTemplate.convertAndSend(exchange.getName(),secondRoutingKey,moneyTransferRequestDto);
            }else{
                Logs logs = new Logs();
                logs.setStatus("404");
                logs.setMessage(moneyTransferRequestDto.getSenderId()+"id'li banka hesabında yeterli bakiye bulunmadığı için işlem gerçekleştirilememiştir.");
                _logRepository.save(logs);
            }},
                ()-> {
                    Logs logs = new Logs();
                    logs.setStatus("404");
                    logs.setMessage(moneyTransferRequestDto.getSenderId()+"id'li kullanıcı bulunamamıştır.");
                    _logRepository.save(logs);
                    System.out.println(moneyTransferRequestDto.getSenderId() +"id'li kullanıcı bulunamamıştır.");
                }
        );
    }

    @RabbitListener(queues = "secondStepQueue")
    public void checkReceiverAccount(MoneyTransferRequestDto moneyTransferRequestDto)
    {
        Optional<Employee> employeeReceiver = _employeeRepository.findById(moneyTransferRequestDto.getReceiverId());
       employeeReceiver.ifPresentOrElse(receiver->{
           receiver.setTotalCash(receiver.getTotalCash()+moneyTransferRequestDto.getAmount());
           _employeeRepository.save(receiver);
           _rabbitTemplate.convertAndSend(exchange.getName(),thirdRoutingKey,moneyTransferRequestDto);
       },
               ()->{
                    Employee employee = _employeeRepository.findById(moneyTransferRequestDto.getSenderId()).orElseThrow();
                    employee.setTotalCash(employee.getTotalCash()+moneyTransferRequestDto.getAmount());
                    _employeeRepository.save(employee);
                    Logs logs = new Logs();
                    logs.setStatus("404");
                    logs.setMessage(moneyTransferRequestDto.getReceiverId()+"id'li hesap bulunamamıştır. Para iade edilmiştir.");
                   _logRepository.save(logs);
               });
    }

    @RabbitListener(queues = "thirdStepQueue")
    public void successProcessNotification(MoneyTransferRequestDto moneyTransferRequestDto)
    {
        Logs logs = new Logs();
        logs.setStatus("200");
        logs.setMessage(moneyTransferRequestDto.getSenderId()+"id'li hesaptan "
                + moneyTransferRequestDto.getReceiverId() + "id'li hesaba başarı ile "
                +moneyTransferRequestDto.getAmount()+"tutarında işlem gerçekleşmiştir");
        _logRepository.save(logs);
    }
}
