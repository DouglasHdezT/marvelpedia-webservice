package me.douglashdezt.simanmarvelpediaws.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "marvel_app_search_histories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String model;
    private String type;
    private String search;
    @Column(name = "limit_value")
    private int limit;
    @Column(name = "offset_value")
    private int offset;
    private String timestamp;

    @ManyToOne
    private User user;
}
