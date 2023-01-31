import com.trifulcas.dao.ActorDAO;

public class TestDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ActorDAO ac=new ActorDAO();
		
		System.out.println(ac.getActor(1));
	}

}
