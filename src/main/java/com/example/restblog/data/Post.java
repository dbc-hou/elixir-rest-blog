package com.example.restblog.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

//import javax.persistence.*;
//import javax.validation.constraints.NotNull;

public class Post {
    private long id;
    private String title;
    private String content;

}
