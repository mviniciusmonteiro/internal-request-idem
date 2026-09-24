/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.marcus.internalrequest.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for XX_InternalRequest
 *  @author iDempiere (generated)
 *  @version Release 13 - $Id$ */
@org.adempiere.base.Model(table="XX_InternalRequest")
public class X_XX_InternalRequest extends PO implements I_XX_InternalRequest, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260924L;

    /** Standard Constructor */
    public X_XX_InternalRequest (Properties ctx, int XX_InternalRequest_ID, String trxName)
    {
      super (ctx, XX_InternalRequest_ID, trxName);
      /** if (XX_InternalRequest_ID == 0)
        {
			setDateRequested (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocumentNo (null);
			setEntityType (null);
// @SQL=SELECT CASE WHEN '@P|AdempiereSys:N@'='Y' THEN 'D' ELSE get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) END FROM Dual
			setName (null);
			setPriority (null);
// 'M'
			setProcessed (false);
// 'N'
			setRequestType (null);
			setRequestedBy_ID (0);
			setStatus (null);
// 'OP'
			setValue (null);
			setXX_InternalRequest_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_XX_InternalRequest (Properties ctx, int XX_InternalRequest_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, XX_InternalRequest_ID, trxName, virtualColumns);
      /** if (XX_InternalRequest_ID == 0)
        {
			setDateRequested (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocumentNo (null);
			setEntityType (null);
// @SQL=SELECT CASE WHEN '@P|AdempiereSys:N@'='Y' THEN 'D' ELSE get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) END FROM Dual
			setName (null);
			setPriority (null);
// 'M'
			setProcessed (false);
// 'N'
			setRequestType (null);
			setRequestedBy_ID (0);
			setStatus (null);
// 'OP'
			setValue (null);
			setXX_InternalRequest_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_XX_InternalRequest (Properties ctx, String XX_InternalRequest_UU, String trxName)
    {
      super (ctx, XX_InternalRequest_UU, trxName);
      /** if (XX_InternalRequest_UU == null)
        {
			setDateRequested (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocumentNo (null);
			setEntityType (null);
// @SQL=SELECT CASE WHEN '@P|AdempiereSys:N@'='Y' THEN 'D' ELSE get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) END FROM Dual
			setName (null);
			setPriority (null);
// 'M'
			setProcessed (false);
// 'N'
			setRequestType (null);
			setRequestedBy_ID (0);
			setStatus (null);
// 'OP'
			setValue (null);
			setXX_InternalRequest_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_XX_InternalRequest (Properties ctx, String XX_InternalRequest_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, XX_InternalRequest_UU, trxName, virtualColumns);
      /** if (XX_InternalRequest_UU == null)
        {
			setDateRequested (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocumentNo (null);
			setEntityType (null);
// @SQL=SELECT CASE WHEN '@P|AdempiereSys:N@'='Y' THEN 'D' ELSE get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) END FROM Dual
			setName (null);
			setPriority (null);
// 'M'
			setProcessed (false);
// 'N'
			setRequestType (null);
			setRequestedBy_ID (0);
			setStatus (null);
// 'OP'
			setValue (null);
			setXX_InternalRequest_ID (0);
        } */
    }

    /** Load Constructor */
    public X_XX_InternalRequest (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_XX_InternalRequest[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_AD_User getAssignedTo() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getAssignedTo_ID(), get_TrxName());
	}

	/** Set Assigned To.
		@param AssignedTo_ID Assigned To
	*/
	public void setAssignedTo_ID (int AssignedTo_ID)
	{
		if (AssignedTo_ID < 1)
			set_Value (COLUMNNAME_AssignedTo_ID, null);
		else
			set_Value (COLUMNNAME_AssignedTo_ID, Integer.valueOf(AssignedTo_ID));
	}

	/** Get Assigned To.
		@return Assigned To	  */
	public int getAssignedTo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AssignedTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Needed.
		@param DateNeeded Date Needed
	*/
	public void setDateNeeded (Timestamp DateNeeded)
	{
		set_Value (COLUMNNAME_DateNeeded, DateNeeded);
	}

	/** Get Date Needed.
		@return Date Needed	  */
	public Timestamp getDateNeeded()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateNeeded);
	}

	/** Set Date Requested.
		@param DateRequested Date Requested
	*/
	public void setDateRequested (Timestamp DateRequested)
	{
		set_Value (COLUMNNAME_DateRequested, DateRequested);
	}

	/** Get Date Requested.
		@return Date Requested	  */
	public Timestamp getDateRequested()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateRequested);
	}

	/** Set Date Resolved.
		@param DateResolved Date Resolved
	*/
	public void setDateResolved (Timestamp DateResolved)
	{
		set_Value (COLUMNNAME_DateResolved, DateResolved);
	}

	/** Get Date Resolved.
		@return Date Resolved	  */
	public Timestamp getDateResolved()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateResolved);
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Document No.
		@param DocumentNo Document sequence number of the document
	*/
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo()
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType Dictionary Entity Type; Determines ownership and synchronization
	*/
	public void setEntityType (String EntityType)
	{

		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType()
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Alta = H */
	public static final String PRIORITY_Alta = "H";
	/** Baixa = L */
	public static final String PRIORITY_Baixa = "L";
	/** Média = M */
	public static final String PRIORITY_Média = "M";
	/** Urgente = U */
	public static final String PRIORITY_Urgente = "U";
	/** Set Priority.
		@param Priority Indicates if this request is of a high, medium or low priority.
	*/
	public void setPriority (String Priority)
	{

		set_Value (COLUMNNAME_Priority, Priority);
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority()
	{
		return (String)get_Value(COLUMNNAME_Priority);
	}

	/** Set Processed.
		@param Processed The document has been processed
	*/
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed()
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** RH = HR */
	public static final String REQUESTTYPE_RH = "HR";
	/** TI = IT */
	public static final String REQUESTTYPE_TI = "IT";
	/** Manutenção = MN */
	public static final String REQUESTTYPE_Manutenção = "MN";
	/** Outro = OT */
	public static final String REQUESTTYPE_Outro = "OT";
	/** Set Request Type.
		@param RequestType Request Type
	*/
	public void setRequestType (String RequestType)
	{

		set_Value (COLUMNNAME_RequestType, RequestType);
	}

	/** Get Request Type.
		@return Request Type	  */
	public String getRequestType()
	{
		return (String)get_Value(COLUMNNAME_RequestType);
	}

	@Deprecated(since="13") // use better methods with cache
	public org.compiere.model.I_AD_User getRequestedBy() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getRequestedBy_ID(), get_TrxName());
	}

	/** Set Requested By.
		@param RequestedBy_ID Requested By
	*/
	public void setRequestedBy_ID (int RequestedBy_ID)
	{
		if (RequestedBy_ID < 1)
			set_Value (COLUMNNAME_RequestedBy_ID, null);
		else
			set_Value (COLUMNNAME_RequestedBy_ID, Integer.valueOf(RequestedBy_ID));
	}

	/** Get Requested By.
		@return Requested By	  */
	public int getRequestedBy_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RequestedBy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Cancelada = CN */
	public static final String STATUS_Cancelada = "CN";
	/** Em Andamento = IP */
	public static final String STATUS_EmAndamento = "IP";
	/** Aberta = OP */
	public static final String STATUS_Aberta = "OP";
	/** Resolvida = RS */
	public static final String STATUS_Resolvida = "RS";
	/** Set Status.
		@param Status Status of the currently running check
	*/
	public void setStatus (String Status)
	{

		set_ValueNoCheck (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus()
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Valid from.
		@param ValidFrom Valid from including this date (first day)
	*/
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom()
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo Valid to including this date (last day)
	*/
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo()
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Internal Request.
		@param XX_InternalRequest_ID Internal Request
	*/
	public void setXX_InternalRequest_ID (int XX_InternalRequest_ID)
	{
		if (XX_InternalRequest_ID < 1)
			set_ValueNoCheck (COLUMNNAME_XX_InternalRequest_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_XX_InternalRequest_ID, Integer.valueOf(XX_InternalRequest_ID));
	}

	/** Get Internal Request.
		@return Internal Request	  */
	public int getXX_InternalRequest_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_XX_InternalRequest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set XX_InternalRequest_UU.
		@param XX_InternalRequest_UU XX_InternalRequest_UU
	*/
	public void setXX_InternalRequest_UU (String XX_InternalRequest_UU)
	{
		set_Value (COLUMNNAME_XX_InternalRequest_UU, XX_InternalRequest_UU);
	}

	/** Get XX_InternalRequest_UU.
		@return XX_InternalRequest_UU	  */
	public String getXX_InternalRequest_UU()
	{
		return (String)get_Value(COLUMNNAME_XX_InternalRequest_UU);
	}
}