package todolist.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "person_id")
    private Person person;
    @Column(name = "description")
    private String description;
    @Column(name = "isdone")
    private boolean isDone;
    @Column(name = "isremove")
    private boolean isRemove;

    public Todo(Person person, String description) {
        this.person = person;
        isDone=false;
        isRemove=false;
        this.description = description;


    }
}
