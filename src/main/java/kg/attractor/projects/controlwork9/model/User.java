package kg.attractor.projects.controlwork9.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false, name = "name")
   private String name;
   @Column(unique = true, nullable = false, name = "email")
   private String email;
   @Column(nullable = false)
   private String password;
   @Column(nullable = false, name = "enabled")
   private Boolean enabled;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "authority_id")
   private Authority authority;
}
