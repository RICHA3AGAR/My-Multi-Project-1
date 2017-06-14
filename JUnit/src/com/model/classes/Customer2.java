package com.model.classes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Customer2 {

    String name;
    int age;
    int id;
    String desc;
    ArrayList<String> list;

    public ArrayList<String> getList()
    {
        return list;
    }

    @XmlElement
    public void setList(ArrayList<String> list)
    {
        this.list = list;
    }

    public String getDesc()
    {
        return desc;
    }

    @XmlElement
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }
}