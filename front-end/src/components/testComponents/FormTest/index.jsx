// import { Button, TextField } from '@material-ui/core';
// import React, { useState } from 'react';

// import api from '../../../services/api';

// import "./styles.css";


// export default function FormMentor({ mentorUpdate, backToUpdate  }) {
//     const [name, setName] = useState("");

//     function handleSubmit(e) {
//         e.preventDefault();
//         if (!(Object.keys(mentorUpdate).length > 1)) {
//             handleCreateMentor(e);
//         } else {
//             handleUpdate(e);
//         }
//     }

//     async function handleCreateMentor(e) {
//         e.preventDefault();
//         console.log(mentorUpdate);
//         const data = {
//             name
//         };
//         try {
//             await api.post('mentor', data).then(response => alert('Mentor: ' + name + ' created'));
//         } catch (err) {
//             alert('Error creating an Mento!!')
//         }
//     }

//     async function handleUpdate(e) {
//         const data = {
//             name
//         };

//         await api.put(`mentor/${mentorUpdate.id}`, data)
//             .then(backToUpdate({},2));
//     }

//     return (
//         <div className="contentForm">
//             <form style={{ width: "100%" }} onSubmit={handleSubmit}>
//                 <TextField
//                     id="name"
//                     name="name"
//                     label="Name"
//                     variant="outlined"
//                     fullWidth
//                     margin="normal"
//                     value={name}
//                     onChange={e => setName(e.target.value)}
//                 />
//                 <Button
//                     fullWidth
//                     type="submit"
//                     variant="contained"
//                     color="primary" 
//                 >
//                     {
//                         Object.keys(mentorUpdate).length > 1 ? "Update" : "Create"
//                     }
//                 </Button>
//             </form>
//         </div>
//     );
// }