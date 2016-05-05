package inventory.ejb;

import inventory.model.Inwrhmtm;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.MaterialGrid;
import estimate.model.MaterialPriceInfo;

/**
 * Session Bean implementation class InventoryBean
 */
@Stateless
public class InventoryBean  extends EmSelector implements InventoryBeanRemote, InventoryBeanLocal {
	
	@EJB
	InmatmDaoRemote inmatmDaoRemote;
	@EJB
	InwrhmapDaoRemote inwrhmapDaoRemote;
	
    /**
     * Default constructor. 
     */
    public InventoryBean() {
        // TODO Auto-generated constructor stub
    }
    
    

	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialPriceInfo> getMaterialPriceInfo(String deptId, String notIn , String webAppName) {//AND TRIM(a.id.matCd) =: matCd
		//getEntityManager(webAppName);
		String resType="MAT-COST";
		BigDecimal resCat=new BigDecimal("1");
		BigDecimal tolerance=new BigDecimal("10");
		//if (resType.equals("LABOUR-COST"))
		//	resType
			
		try{
			List<MaterialPriceInfo> infos=new ArrayList<MaterialPriceInfo>();
			//WHERE a.id.deptId =: deptId
			//String notIn="'00117',"+"'00120'";
			System.out.println(notIn);
			String strquery=null;
			if (notIn==null || notIn.equals("") || notIn.equals(" ") || notIn.length()==0 || notIn.length()==1)
				strquery="select a from Inwrhmtm a WHERE a.id.deptId =:deptId ORDER BY a.id.matCd";
			else
				strquery="select a from Inwrhmtm a WHERE a.id.deptId =:deptId AND a.id.matCd NOT IN ("+notIn+") ORDER BY a.id.matCd";
			Query query = getEntityManager(webAppName).createQuery(strquery);
			query.setParameter("deptId", deptId);
			//query.setParameter("notIn", notIn);
			List<Inwrhmtm> list = query.getResultList();
			Iterator<Inwrhmtm> it = list.iterator();
			while (it.hasNext()) 
	        { 
				MaterialPriceInfo info=new MaterialPriceInfo();
				Inwrhmtm inwrhmtm=it.next();
				info.setMatCd(inwrhmtm.getId().getMatCd().trim());
				info.setMatNm(inmatmDaoRemote.findName(info.getMatCd(), webAppName).trim());
				info.setUom(inwrhmtm.getUomCd());
				info.setUnitPrice(inwrhmtm.getUnitPrice());
				info.setResType(resType);
				info.setResCat(resCat);
				info.setTolerance(tolerance);
				infos.add(info);
	        }
			System.out.println("sbksjdjksdnfjksdnfkjsdfkjsdhfkjsdhfjhsdfjds");
			return infos;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/*@SuppressWarnings("unchecked")
	@Override
	//new estimate.model.MaterialGrid(TRIM(p.resType), TRIM(p.id.resCd), p.resCat, TRIM(i.matNm), TRIM(p.uom), p.tolerance, p.unitPrice, p.estimateQty, p.estimateCost, p.customerQty, p.damageQty)  from Pcestdtt p , Inmatm i 
	public List<MatInfo> getMatListByCategory(String deptId, String like) {
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId);
		
		//String qryStr = "select TRIM(a.resType), a.resCat, TRIM(a.id.matCd), TRIM(a.matNm), TRIM(a.uom), a.unitPrice, a.tolerance   from Inmatm a WHERE TRIM(a.matCd) like :like";		Query query = getEntityManager(webAppName).createQuery(qryStr);
		String qryStr = "select new inventory.model.MatInfo (TRIM(a.id.matCd), TRIM(b.matNm)) from Inwrhmtm a, Inmatm b WHERE a.id.matCd=b.id.matCd AND a.id.deptId=:deptId AND TRIM(a.id.matCd) like :like ORDER BY a.id.matCd ASC";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", warehouse);
		query.setParameter("like", like+"%");
		List<MatInfo> list = query.getResultList();
		return list;
	}*/
	

	@SuppressWarnings("unchecked")
	@Override
	//new estimate.model.MaterialGrid(TRIM(p.resType), TRIM(p.id.resCd), p.resCat, TRIM(i.matNm), TRIM(p.uom), p.tolerance, p.unitPrice, p.estimateQty, p.estimateCost, p.customerQty, p.damageQty)  from Pcestdtt p , Inmatm i 
	public List<MaterialGrid> getMatListByCategory(String deptId, String like, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		
		//String qryStr = "select TRIM(a.resType), a.resCat, TRIM(a.id.matCd), TRIM(a.matNm), TRIM(a.uom), a.unitPrice, a.tolerance   from Inmatm a WHERE TRIM(a.matCd) like :like";		Query query = getEntityManager(webAppName).createQuery(qryStr);
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(a.id.matCd), TRIM(b.matNm), a.unitPrice, a.uomCd) from Inwrhmtm a, Inmatm b WHERE a.id.matCd=b.id.matCd AND a.id.deptId=:deptId AND TRIM(a.id.matCd) like :like ORDER BY a.id.matCd ASC";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", warehouse);
		query.setParameter("like", like+"%");
		List<MaterialGrid> list = query.getResultList();
		return list;
	}

	

}
