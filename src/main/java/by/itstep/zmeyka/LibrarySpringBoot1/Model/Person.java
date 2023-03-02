package by.itstep.zmeyka.LibrarySpringBoot1.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="person")
public class Person {
  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  @NotEmpty(message="can't be empty")
  @Size(min=2, max=35, message="Name should be shorter.try again.")
  @Column(name="name")
    private String name;
    @NotEmpty(message="can't be empty")
    @Size(min=2, max=35, message="Name should be shorter.try again.")
    @Column (name="surname")
    private String surname;

    @NotEmpty(message="can't be empty")
    @Column(name="email")
    private String email;
   @Column(name="password")
   private String password;

    @OneToMany(mappedBy = "borrower")
    private List<Book>books;
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="id_person", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="id_roles", referencedColumnName="id")})
    private List<Role> roles = new ArrayList<>();



}
