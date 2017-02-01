package hello.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Victor on 26-Jan-17.
 */
@Entity
@Table(name = "server")
public class Server {

    private Integer serverId;
    private String serverName;
    private String ip;
    private Integer capacity;
    private Set<CatwInstance> catws = new HashSet<CatwInstance>();

    public Server(){}

    @Id
    @GeneratedValue
    @Column(name = "server_id")
    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    @Column(name = "server_name")
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "server")
    public Set<CatwInstance> getCatws() {
        return catws;
    }

    public void setCatws(Set<CatwInstance> catws) {
        this.catws = catws;
    }

    public void addCatws(CatwInstance catw) {
        this.catws.add(catw);
    }
}
