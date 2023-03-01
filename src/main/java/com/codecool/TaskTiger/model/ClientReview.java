package com.codecool.TaskTiger.model;

import com.codecool.TaskTiger.model.user.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientReview {

  @Id
  @SequenceGenerator(name = "clientReview_sequence",
          sequenceName = "clientReview_sequence",
          allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "clientReview_sequence")
  @Column(name = "clientReviewId", updatable = false)
    private Long id;

  @Column
  private UserModel revieweduser;

  @Column
    private int reviewValue;

  @Column
  private String description;

}
