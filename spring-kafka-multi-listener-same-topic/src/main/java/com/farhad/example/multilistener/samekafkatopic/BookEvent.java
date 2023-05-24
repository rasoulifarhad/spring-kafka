package com.farhad.example.multilistener.samekafkatopic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEvent {
    
    private String title;
    private String description;
    private Double price;
}
