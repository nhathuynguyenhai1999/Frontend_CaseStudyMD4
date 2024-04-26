package com.example.casestudylibrary.domain;

import com.example.casestudylibrary.domain.dto.res.CategoryResDto;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Where(clause = "deleted <> 'DELETED'")
    private List<Book> books;

    public CategoryResDto toCategoryResDto() {
        return new CategoryResDto(id, name);
    }
}
