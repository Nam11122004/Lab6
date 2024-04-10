package com.example.lab5_6.handle;


import com.example.lab5_6.Model.Students;

public interface Item_Student_Handle {
    public void Delete(String id);
    public void Update(String id, Students students);
}
