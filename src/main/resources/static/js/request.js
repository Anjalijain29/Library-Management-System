var _html = ""
for (let i = 0; i < 10; i++) {

    _html+='<tr>'
    _html +='<td>R12356</td>'
    _html +='<td>M5456</td>'
    _html +='<td>Harry Potter and the Order of the Phoenix</td>'
    _html +='<td>19.02.2022</td>'
    _html +='<td><button type="button" class="btn btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">'
    _html +='Click Here'
    _html +='</button>'
    _html +='</td>'
    _html +='</tr>'

}


$('.tbd').empty().html(_html);


//dynamically rendering model

// var name = "admin"
// var model = ""

//   model+='<div class="modal-content">'
//   model+= '<div class="modal-header">'
//   model+=   '<h5 class="modal-title" id="exampleModalLabel">Book Details</h5>'
//   model+=    '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>'
//   model+= '</div>'
//   model+=  '<div class="modal-body">'

//   model+=   '<div class="row">'
//   model+=     '<div class="col-4">'
//   model+=     '<p class="text-center"><i class="fas fa-book fa-4x" style="color: indianred;"></i></p>'
//   model+=      '</div>'

//   model+=     '<div class="col-8">'
//   model+=      '<ul class="p-0" style="list-style: none;">'
//   model+=         '<li>Book Name </li>'
//   model+=         '<li>Book Name </li>'
//   model+=         '<li>Book Name </li>'
//   model+=         '<li>Book Name </li>'
//   model+=       '</ul>'
//   model+=        '<h2>'
//   model+=          '<span class="badge text-dark p-0">v52gs1</span>'
//   model+=        '</h2>'
//   model+=      '</div>'
//   model+=    '</div>'
//   model+= '</div>'

//   model+= '<div class="modal-footer justify-content-center">'
// if (name == "admin") {

//     model += '<button type="button" class="btn btn-outline-warning waves-effect mx-3">Add</button>'
//     model += '<button type="button" class="btn btn-outline-success waves-effect mx-3">Issue</button>'
//     model += '<button type="button" class="btn btn-outline-danger waves-effect mx-3">Disable</button>'
// }
// else {
//     model += '<button type="button" class="btn btn-outline-success waves-effect mx-3">Request</button>'
// }

//   model+= '</div>'
//   model+='</div>'
//   model+= '</div>'
//   model+='</div>'

// $('.view').on('click', function () {
//     console.log("clicked")
//     $('.modal-footer').empty().html(model);
// })