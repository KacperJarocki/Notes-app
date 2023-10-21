package server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpaceAccesses {
	@Id
	private int accessId;
	@Column(name = "space_id")
	private int spaceId;
	@Column(name = "account_id")
	private int accountId;
	private Boolean read;
	private Boolean write;
}
