package by.itstep.zmeyka.LibrarySpringBoot1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message="book can't be empty")
    @Size(min=2, max=35, message="The title of the book should be shorter.try again.")
   @Column(name="title")
    private String title;
    @NotEmpty(message=" The author of the book can't be empty")
    @Size(min=1, max=35, message="The author of the book should be shorter.try again.")
    @Column(name="author")
    private String author;
    @Min(value = 1700, message = "The book shouldn't be older than 1700")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name="id_person", referencedColumnName = "id")
    private Person borrower;


    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;
    @Transient
    private boolean expired;


}
