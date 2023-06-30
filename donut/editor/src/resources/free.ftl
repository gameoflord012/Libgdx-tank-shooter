<#macro tree map>
    <#list map?keys as key>
        <#if map[key]?has_content>
            public static class ${key}
            {
                <@tree map[key] />
            }
        <#else>
            public static File ${key} = new File(map[key]);
        </#if>
    </#list>
</#macro>

package ${package}

public class Assets
{
    <@tree data />
}



