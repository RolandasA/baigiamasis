package lt.Rolandas.basketball.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "age")
    private int age;

    @Column(name = "height")
    private int height;

    @Column(name = "your_score")
    private int your_score;

    @Column(name = "opponent_score")
    private int opponent_score;


    public User(String username, int age, int height, int your_score, int opponent_score) {
        this.username = username;
        this.age = age;
        this.height = height;
        this.your_score = your_score;
        this.opponent_score = opponent_score;
    }
}
