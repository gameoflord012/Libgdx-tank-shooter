<#macro tree parent>
    <#if parent.childNodes??>
        <#list parent.childNodes as node>
            public static class ${node.name}
            {
                <@tree parent=node />
            }
        </#list>
    </#if>
</#macro>
package ${packageName}

public class Assets
{
    <@tree parent=root />
}



