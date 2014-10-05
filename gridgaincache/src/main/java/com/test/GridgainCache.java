package com.test;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.gridgain.grid.GridGain;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Portlet implementation class GridgainCache
 */
public class GridgainCache extends GenericPortlet {
	
	private static void startCache(){
		try {
			_log.info("Initializing cache");
			GridGain.start("/home/emperor/gg_liferay/liferay-gg-config.xml");
			_log.info("Cache initialized");
		}catch(Exception e){
			_log.error("ERROR", e);
			throw new RuntimeException(e); 
		}
	}
    public void init() {
        viewTemplate = getInitParameter("view-jsp");
        _log.info("Initializing portlet");
        startCache();
        _log.info("Portlet initialized");
    }

    public void processAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {

        super.processAction(actionRequest, actionResponse);
    }

    public void serveResource(
            ResourceRequest request, ResourceResponse response)
        throws PortletException, IOException {

        super.serveResource(request, response);
    }

    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(viewTemplate, renderRequest, renderResponse);
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
 
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(GridgainCache.class);

}
