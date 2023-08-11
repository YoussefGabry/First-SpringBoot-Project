package com.gabry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    private final CustomerRepository customerRepository;

    record newCustomerRequest(
            String name,
            String email,
            Integer age
    ){}

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping
    public void createCustomer(@RequestBody newCustomerRequest newCustomerRequest){
        Customer customer = new Customer();
        customer.setAge(newCustomerRequest.age);
        customer.setEmail(newCustomerRequest.email);
        customer.setName(newCustomerRequest.name);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }


//    @GetMapping("/greet")
//    public greetResponse greet(){
//        return new greetResponse(
//                "Hello",
//                List.of("Java","C#","C++"),
//                new Person("Youssef",25,12.5));
//    }
//
//    record Person(String name, int age, double cash){}
//    record greetResponse(
//            String greet,
//            List<String> favProgramingLanguages,
//            Person person
//    ){}
}
