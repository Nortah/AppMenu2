package com.example.appmenu.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "type")
public class TypeEntity
{
    @PrimaryKey
    private Long id;
    @ColumnInfo(name = "name")
    private String name;
     @ColumnInfo(name = "image")
    private String image;
//Constructor
    public TypeEntity(String name, String image)
    {
        this.name = name;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
