package store.entities;

import jakarta.persistence.*;
import lombok.*;
import store.extendedInfo.ExtendedCustomerInfo;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "customers", schema = "public")
public class Customers extends BaseEntity<Long> {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Embedded
    private ExtendedCustomerInfo customerInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

}
