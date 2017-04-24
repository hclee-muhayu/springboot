package com.muhayu;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by hyecheon on 2017. 4. 16..
 */
@Entity
@Table(name = "myData")
@Data
public class MyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column(length = 50, nullable = false)
    @NotEmpty
    private String name;

    @Column(length = 200, nullable = false)
    @Email
    private String mail;
    @Column(nullable = true)
    @Min(0)
    @Max(200)
    private Integer age;

    @Column(nullable = true)
    private String memo;
}
