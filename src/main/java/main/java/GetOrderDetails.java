package main.java;

import java.util.List;

public class GetOrderDetails {

    @Override
    public String toString() {
        return "GetOrderDetails{" +
                "message='" + message + '\'' +
                ", Data=" + Data +
                '}';
    }

    private String message;
    private OrderData Data;


    public OrderData getData() {
        return Data;
    }

    public void setData(OrderData data) {
        Data = data;
    }





    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
