package com.kpanda.shop.notice;

import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;
    public String date;
}
