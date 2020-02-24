<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>Welcome ${user}<#if user == "Big Joe">, our beloved leader</#if>!</h1>
  <p>Our latest product:
  <a href="${latestProduct.url}">${latestProduct.name}</a>!
  
  
<#assign seq = [1,2,3,4,5,6,7,8,9]>

1.	start..end
<#list seq[1..3] as i>${i}</#list>
<#list seq[3..1] as i>${i}</#list>

2.	start..<end 或者 start..!end
<#list seq[1..<3] as i>${i}</#list>
<#list seq[3..<1] as i>${i}</#list>
<#list seq[1..!3] as i>${i}</#list>
<#list seq[3..!1] as i>${i}</#list>

3.	start..*length
<#list seq[3..*3] as i>${i}</#list>
<#list seq[3..*-3] as i>${i}</#list>

4.	start..
<#list seq[3..] as i>${i}</#list>

<#-- <#assign s = "Hello ${item}!"> -->
<#assign s = "Hello " +item>
${s[2..3]}
${s[2..<4]}
${s[2..*100]}
${s[2..]}

${item[0]}
${item[4]}
</body>
<#macro greet>
  <font size="+2">Hello Joe!</font>
</#macro>
<@greet></@greet>
</html>