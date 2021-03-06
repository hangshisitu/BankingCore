/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;


import com.bytecode.customexporter.MyConverter;
import com.bytecode.dto.ChequeStatus;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class ReportMBean {

    private boolean exporting;
    private MyConverter myconverter;
    /**
     * Creates a new instance of ReportMBean
     */
    public ReportMBean() {
    }

    public MyConverter getMyconverter() {
        return myconverter;
    }

    public void setMyconverter(MyConverter myconverter) {
        this.myconverter = myconverter;
    }

    public boolean isExporting() {
        return exporting;
    }

    public void setExporting(boolean exporting) {
        this.exporting = exporting;
    }
    
    
     public ChequeStatus[] getChequeStatuses() {
        return ChequeStatus.values();
    }
    
}
