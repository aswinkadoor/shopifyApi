<table id="dataTable" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
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

<script type="application/javascript">
    $('#dataTable').DataTable({
        "paging": 'simple_numbers' // false to disable pagination (or any other option)
    });
    $('.dataTables_length').addClass('bs-select');
</script>
