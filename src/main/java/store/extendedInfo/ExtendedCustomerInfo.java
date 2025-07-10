package store.extendedInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ExtendedCustomerInfo {
    private String email;
    
    @Column(name = "phone_Number", unique = true)
    private String phoneNumber;
}
