package com.hrp.controller.common;

import com.hrp.entity.system.User;
import com.hrp.utils.Constant;
import com.hrp.utils.Jurisdiction;
import com.hrp.utils.PageData;
import com.hrp.utils.UuidUtil;
import com.hrp.utils.lang.StringUtil;
import com.hrp.utils.plugins.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * BaseController
 *
 * @author KVLT
 * @date 2017-03-23.
 */
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    protected PageData pd = new PageData();

    public static String URL404 = "404.jsp";
    public static String URL500 = "500.jsp";

    // 获取请求次数
    protected String draw = "0";
    // 数据起始位置
    protected String start;
    // 数据长度
    protected String length;
    // 总记录数
    protected int recordsTotal = 0;
    // 过滤后记录数
    protected int recordsFiltered = 0;
    // 定义列名
    protected String[] cols = {};
    // 获取客户端需要那一列排序
    protected String orderColumn = "0";
    // 获取排序方式 默认asc
    protected String orderDir = "asc";
    // 获取用户过滤框里的字符
    protected String searchValue;

	/**
	 * 获取当前登录用户
	 * @return
	 */
	public User getCurUser(){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User curUser = (User) session.getAttribute(Constant.SESSION_USER);
		return  curUser;
	}

	/**
     * new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}

    /**
     * 从页面DataTable获取分页参数
     */
	public void getPageParam() {
        // 获取文件参数
        HttpServletRequest request = this.getRequest();
        draw = request.getParameter("draw");
        start = request.getParameter("start");
        length = request.getParameter("length");
        orderColumn = request.getParameter("order[0][column]");
        orderColumn = request.getParameter("columns[0][name]");
        //orderColumn = cols[Integer.parseInt(orderColumn)];
        orderDir = request.getParameter("order[0][dir]");
        searchValue = request.getParameter("search[value]");
    }

    /**
     * 设置page
     */
    public void setPage(Page page) {
	    page.setShowCount(Integer.parseInt(length));
	    page.setCurrentResult(Integer.parseInt(start));
	    page.setCurrentPage(Integer.parseInt(start) / page.getShowCount() + 1);
	    page.setOrderColumn(orderColumn);
	    page.setOrderDir(StringUtil.isNotBlank(orderDir) ? orderDir : "asc");
    }

	
	/**
     * 得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}

    /**
     * 得到ModelAndView
     * @return
     */
    public ModelAndView getModelAndView(String viewName){
        return new ModelAndView(viewName);
    }
	
	/**
     * 得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
     * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
	/**
     * 得到分页列表的信息
	 * @return
	 */
	public Page getPage(){
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
	public User getCurrentUser(){
		Session session = Jurisdiction.getSession();
		User user = (User)session.getAttribute(Constant.SESSION_USER);
		return user;
	}

    /**
     * 往Request里插值
     * @param request
     * @param key
     * @param value
     */
    protected static void setValue2Request(HttpServletRequest request, String key, Object value) {
        request.setAttribute(key, value);
    }

    /**
     * 获取session
     * @param request
     * @return
     */
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    public ModelAndView redirect(String redirectUrl, PageData pd) {
        ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
        if (null != pd && pd.size() > 0) {
            view.addAllObjects(pd);
        }
        return view;
    }

    public ModelAndView redirect404(){
        return new ModelAndView(new RedirectView(URL404));
    }


}
