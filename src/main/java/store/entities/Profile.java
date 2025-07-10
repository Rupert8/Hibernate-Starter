package store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Profile {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Users user;

    private String street;

    private String language;

    public void setUser(Users user) {
        this.user = user;
        user.setProfile(this);
        id = user.getId();
    }

}
