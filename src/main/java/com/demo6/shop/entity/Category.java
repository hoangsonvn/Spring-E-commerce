package com.demo6.shop.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Product> products;
}
