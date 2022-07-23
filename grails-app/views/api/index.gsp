<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Events</title>
</head>
<body>
<div class="col-sm-12">
    <h4 class="font-weight-bold" style="font-size: x-large"><i class="fa fa-envelope"></i> Events </h4>
</div>

<div align="right" class="col-sm-12">
%{--    <g:form method="post" name="sync" action="index" >--}%
        <button class="btn btn-outline-primary"  value="${'sync'}" type="submit" style="horiz-align: right" id="syncButton">Sync</button>
%{--        <g:hiddenField name="sync" value="${'sync'}"/>--}%
%{--    </g:form>--}%
</div>
<br>

<div class="col-sm-12">
    <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th class="th-sm">Transaction Id</th>
            <th class="th-sm">Author</th>
            <th class="th-sm">Subject Type</th>
            <th class="th-sm">Message</th>
            <th class="th-sm">Created date</th>
            <th class="th-sm">Verb</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${result}" status="i" var="trans">
            <tr>
                <td>${result[i].transactionId}</td>
                <td>${result[i].author}</td>
                <td>${result[i].subjectType}</td>
                <td>${result[i].message}</td>
                <td>${result[i].createdAt}</td>
                <td>${result[i].verb}</td>

            </tr>

        </g:each>
        </tbody>
    </table>


</div>


<asset:javascript src="local/api"/>

</body>
</html>