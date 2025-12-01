package com.Library.LibraryManagement.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "LIBRARY")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer libId ;
    private String libName ;
    private Integer bookCount;
    private String branchName;
    private long mobileNumber;
    private Integer subscriptionCount;
    private Integer bookList;

}
