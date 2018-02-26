package io.github.victorhugonf.javaee.ejb.logerro;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import io.github.victorhugonf.javaee.ejb.entity.LogError;
import io.github.victorhugonf.javaee.ejb.service.LogErrorService;

@Stateless
@LocalBean
public class LogErrorInterceptor {
	
	@EJB
	private LogErrorService logErrorBO;
	
	protected LogErrorService logErroBO() {
//		if(_logErroBO == null){
//			_logErroBO = new LogErroBO(); 
//		}
		return logErrorBO;
	}
	
	@SuppressWarnings("finally")
	@AroundInvoke
	public Object logError(InvocationContext context) throws Exception{
		try{
			return context.proceed();
		}catch(Exception e){
			try{
				handleError(e);
			}catch(Exception ex){
				
			}finally{
				throw e;
			}
		}
	}
	
	private void handleError(Exception e) throws Exception{
		try{
			logErroBO().persist(new LogError(e));
		}catch(Exception ex){
			
		}
	}

}
