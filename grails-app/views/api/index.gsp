<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Events</title>
</head>
<body>
<br>



<div class="row">
    <div class="col-11">
        <legend class="font-weight-bold" style="font-size: x-large"><i class="fa fa-envelope"></i> Events </legend>
    </div>

    <div align="right" class="col-1">
        <button style="width: 100px" class="btn btn-outline-danger" value="${'sync'}" type="submit" style="horiz-align: right" id="syncButton">Sync</button>
    </div>
    <br>
    
    <div class="col-4">
        <div class="form-group">
            <label for="fromDate">From Date</label>
            <g:field type="date" class="form-control" name="fromDate"/>
        </div>
    </div>
    <div class="col-4">
        <div class="form-group">
            <label for="toDate">To Date</label>
            <g:field type="date" class="form-control" name="toDate"/>
        </div>
    </div>
    <div class="col-4">
        <br>
        <button type="submit" id="searchBtn" class="btn btn-primary">Search</button>
    </div>
    <hr>
</div>
<div class="col-sm-12">
    <div id="dataTemplate"></div>
</div>


<asset:javascript src="local/api"/>

</body>
</html>