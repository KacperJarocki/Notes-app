package server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UrlAddresses {
	@Id
	@Column(name = "url_id")
	private int urlId;
	private String urlAddress;
}
