/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const inputs = document.querySelectorAll("input"),
    button = document.querySelector("button");

inputs.forEach((input, index1) => {
    input.addEventListener("keyup", (e) => {
        const currentInput = input,
            nextInput = input.nextElementSibling,
            prevInput = input.previousElementSibling;

            if(currentInput.value.length > 1){
                currentInput.value = "";
                return;
            }
            if(nextInput && nextInput.hasAttribute("disabled") && currentInput.value != ""){
                nextInput.removeAttribute("disabled");
                nextInput.focus();
            }
            if(e.key == "Backspace"){
                inputs.forEach((input, index2) => {
                    if(index1 <= index2 && prevInput){
                        input.setAttribute("disabled", true);
                        currentInput.value = "";
                        prevInput.focus();
                    }
                });
            }
            if(!inputs[5].disabled){
                button.classList.add("active");
                return;
            }
            button.classList.remove("active");
    });
});
//focus vao o dau tien khi load
window.addEventListener("load", () => inputs[0].focus());

