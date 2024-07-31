package repository2;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.senai.classes.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
