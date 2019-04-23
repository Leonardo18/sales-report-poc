package com.poc.sales.report.service;

import com.poc.sales.report.factory.CustomerFactory;
import com.poc.sales.report.model.CustomerModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;

@Service
public class CustomerService {

    private CustomerFactory customerFactory;

    public CustomerService(CustomerFactory customerFactory) {
        this.customerFactory = customerFactory;
    }

    public CustomerModel buildCustomerByMatcher(Matcher matcher) {
        return customerFactory.create(matcher);
    }

    public Integer getTotalCustomer(List<CustomerModel> customerModelList) {
        return customerModelList.size();
    }
}
