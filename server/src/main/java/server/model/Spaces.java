package server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Spaces {
	@Id
	@Column(name = "space_id")
	private int spaceId;
	@Column(name = "space_creator_id")
	private int spaceCreatorId;
	private String title;
	@Column(name = "modification_date")
	private LocalDateTime modificationDate;
	@Column(name = "url_address_id")
	private int urlAddressId;
}
