package ro.siit.airports.domain;
import javax.persistence.*;

@Entity
    @Table(name = "airlines")
    public class Airline {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private long id;

        @Column(name = "name")
        private String name;

        @Column(name = "alias")
        private String alias;

        @Column(name = "iata", length = 10)
        private String iata;

        @Column(name = "icao", length = 10)
        private String icao;

        @Column(name = "callsign")
        private String callsign;

        @Column(name = "country")
        private String country;

        @Column(name = "active")
        private Boolean active;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getIata() {
            return iata;
        }

        public void setIata(String iata) {
            this.iata = iata;
        }

        public String getIcao() {
            return icao;
        }

        public void setIcao(String icao) {
            this.icao = icao;
        }

        public String getCallsign() {
            return callsign;
        }

        public void setCallsign(String callsign) {
            this.callsign = callsign;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }
    }
