async function showComments(ids) {
    if (document.getElementById('tab') != null) {
        document.querySelector('#tab').remove()
    }
    $('#wrap').append(`
    <div>
        <table id="tab" class="comments">
            <thead>
                <tr>
                    <th id="comment"> Comments for book:</th>
                </tr>
            </thead>
        </table>
    </div>
    `)
    let response = await fetch('/book/' + ids);
    if (response.ok) {
        let title = await response.text();
        $('#comment').append(`${title}`);
    }
    response = await fetch('/comments/' + ids);
    if (response.ok) {
        let comments = await response.json();
        comments.forEach(function (comment, i, comments) {
            $('#tab').append(`
                       <tr>
                          <td>${comment.text}</td>
                       </tr>
                   `)
        });
        $('#tab').append(`
      <button type="button" onclick="document.querySelector('#tab').remove()">close</button>
                   `)
    }
};


