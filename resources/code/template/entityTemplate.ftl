package ${bussiPackage}.${entityPackage}.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: ${ftl_description}
 * @author JiangBo
 *
 */
@Entity
@Table(name = "${tableName}")
public class ${entityName}Entity implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
	<#list originalColumns as po>
	/**${po.filedComment}*/
	private ${po.fieldType} ${po.fieldName};
	</#list>
	
	<#list originalColumns as po>
	/**
	 *方法: 取得${po.fieldType}
	 *@return: ${po.fieldType}  ${po.filedComment}
	 */
	<#if po.fieldName == cg_table_id>
	
	<#if cg_primary_key_policy == 'uuid'>
	@Id
	</#if>
	<#if cg_primary_key_policy == 'identity'>
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	</#if>
	<#if cg_primary_key_policy == 'sequence'>
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="sequence")
	@SequenceGenerator(name="sequence",sequenceName="${cg_sequence_code}",allocationSize=1)
	</#if>
	</#if>
	@Column(name ="${po.fieldDbName}",nullable=<#if po.nullable == 'Y'>true<#else>false</#if><#if po.precision != ''>,precision=${po.precision}</#if><#if po.scale != ''>,scale=${po.scale}</#if><#if po.charmaxLength != ''>,length=${po.charmaxLength}</#if>)
	public ${po.fieldType} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.fieldType}
	 *@param: ${po.fieldType}  ${po.filedComment}
	 */
	public void set${po.fieldName?cap_first}(${po.fieldType} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#list>
}
