package com.example.demo.lesson.spring.simplestart.event;

import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {
    private Object object;

    public OrderEvent(Object source, Object t) {
        super(source);
        this.object = t;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
