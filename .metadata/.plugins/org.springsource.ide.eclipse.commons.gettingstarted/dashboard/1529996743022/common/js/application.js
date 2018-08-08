//import react
import React from 'react'
import ReactDOM from 'react-dom'

//import redux
import { connect } from 'react-redux'

//import third-party
import { Alert, Form, FormControl, FormGroup, Table, Checkbox } from 'react-bootstrap'
import { ControlLabel } from "react-bootstrap";
import trim from 'trim';

// import Dialog from 'react-bootstrap-dialog'
import Dialog from 'react-dialog'

//import local
import { getPropsMap } from './tasksReducer'
import { tasksSubmit, editSubmit, getAllData, deleteTask } from './tasksAction'

//import css
import './tasks.css'

let checkBoxArr = []; let taskNameAlert = false; let taskDescAlert = false; let showDelAlert = false; let delSuccessAlert = false;

class Tasks extends React.Component {
    constructor() {
        super();
        this.state = {
            isDialogOpen: false,
            isEditDialogOpen: false,
            isShowPage: true
        }
    }
    componentDidMount() {
        this.props.getData();
        // this.props.delData();
    }

    openDialog = () => {
        this.setState({ isDialogOpen: true, isShowPage: false, isEditDialogOpen: false });
        taskNameAlert = false; taskDescAlert = false;
    }


    editData = '';
    openEditDialog = (row) => {
        this.editData = '';
        this.setState({ isEditDialogOpen: true, isShowPage: false, isDialogOpen: false });
        this.editData = row;
        console.log(this.editData);
        taskNameAlert = false; taskDescAlert = false;
    }


    handleClose = () => this.setState({ isDialogOpen: false, isShowPage: true, isEditDialogOpen: false })




    handleInputChange(event) {
        showDelAlert = false;
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        console.log(value);
        const checkedvalue = target.name;
        console.log(checkedvalue);

        if (value == true) {
            checkBoxArr.push(checkedvalue);
        }
        if (value == false) {
            for (let i = 0; i < checkBoxArr.length; i++) {
                if (checkBoxArr[i] == checkedvalue) {
                    checkBoxArr.splice(i, 1);

                }
            }
        }
        console.log("checkboxarr is:" + checkBoxArr);
    }

    addReset = () => {
        document.getElementById("ahi-task-form").reset();
        taskNameAlert = false; taskDescAlert = false;
        this.setState({ isDialogOpen: true, isShowPage: false, isEditDialogOpen: false });
    }
    editReset = () => {
        document.getElementById("ahi-task-edit-form").reset();
        taskNameAlert = false; taskDescAlert = false;
        this.setState({ isDialogOpen: false, isShowPage: false, isEditDialogOpen: true });
    }
    render() {

        const { addSubmit, editSubmit, errorMessage, isFetching, taskData, delData, userData } = this.props;
        let taskNameInput = ''; let descInput = '';
        console.log("userData is:" + JSON.stringify(userData));

        function handledelete() {
            if (checkBoxArr != '') {
                delSuccessAlert = true;
                console.log("deletetask is executing...");
                delData(checkBoxArr);

                checkBoxArr.length = 0;
                window.location.reload();
            }
            else {
                console.log("delete alert is working");
                showDelAlert = true; delSuccessAlert = false;

            }
        }

        return (

            <div>
                {this.state.isShowPage && <h1>MY TASKBOARD</h1>}

                <div className="container">
                    {this.state.isShowPage &&
                        <div>
                            {showDelAlert ? (<Alert bsStyle="danger">Please Seelct a task!</Alert>) : null}
                            {delSuccessAlert ? (<Alert bsStyle="success" >Your task is successfully deleted!</Alert>) : null}

                            <button type="button" onClick={this.openDialog} className="btn btn-primary adddisplay" >Add Task</button>
                            <button type="button" className="btn btn-danger deldisplay" onClick={handledelete} >Del Task</button>
                            <br /><br />
                        </div>
                    }
                    {/* ----------------------------------------add task form---------------------------------------- */}
                    {
                        this.state.isDialogOpen &&

                        <div>
                            <h1>ADD NEW TASK</h1>
                            <Form className="ahi-task-form" id="ahi-task-form" onSubmit={(e) => {
                                e.preventDefault();
                                let datas = ''; taskDescAlert = false; taskNameAlert = false;

                                 taskNameInput.value = trim( taskNameInput.value);
                                descInput.value = trim(descInput.value);
                                console.log("taskNameInput is: " +  taskNameInput.value);

                                if ( taskNameInput.value != '' && descInput.value != '') {
                                    datas = {  taskId: null,  taskName:  taskNameInput.value,  taskDescription: descInput.value };
                                    addSubmit(datas);
                                    // window.location.reload();
                                }
                                else if ( taskNameInput.value == '') {
                                    console.log(" taskname alert is working");
                                     taskNameAlert = true;
                                    this.setState({ isDialogOpen: true, isShowPage: false, isEditDialogOpen: false });
                                }
                                else if (descInput.value == '') {
                                    console.log("desc alert is working");
                                     taskDescAlert = true;
                                    this.setState({ isDialogOpen: true, isShowPage: false, isEditDialogOpen: false });
                                }
                                
                            }
                            }>
                                <FormGroup >
                                    {errorMessage ? (<Alert bsStyle="danger"><strong>Error!</strong> {errorMessage}</Alert>) : null}
                                </FormGroup>

                                <FormGroup >
                                    {isFetching ? (<Alert bsStyle="success" >Your Task is successfully Added!</Alert>) : null}
                                </FormGroup>

                                <FormGroup >
                                    {taskNameAlert ? (<Alert bsStyle="danger" >Please Enter task name!</Alert>) : null}
                                </FormGroup>
                                <FormGroup >
                                    {taskDescAlert ? (<Alert bsStyle="danger" >Please Enter task description!</Alert>) : null}
                                </FormGroup>

                                <FormGroup controlId="formControlsSelect" className="ahi- task-name">
                                    <ControlLabel>Task Name</ControlLabel>
                                    <FormControl type="string" placeholder="enter TaskName"
                                        inputRef={(ref) => {
                                            taskNameInput = ref
                                        }}
                                    />
                                </FormGroup>

                                <FormGroup className="ahi- task-desc">
                                    <ControlLabel>Description</ControlLabel>
                                    <FormControl type="string" placeholder="enter description"
                                        inputRef={(ref) => {
                                            descInput = ref
                                        }}
                                    />
                                </FormGroup>
                                <br />

                                <FormGroup>
                                    <button className="btn btn-primary canceldisplay" onClick={this.handleClose} >Cancel</button>
                                    <button type="submit" className="btn btn-success  resetdisplay" >Submit</button>
                                    <button type="button" className="btn btn-info  resetdisplay" onClick={this.addReset} >Reset</button>
                                </FormGroup>
                            </Form>

                        </div >
                    }
                    {/* -------------------------------------------------------------edit task form---------------------------------------- */}
                    {
                        this.state.isEditDialogOpen &&
                        <div>
                            <h1>EDIT TASK</h1>
                            <Form className="ahi-task-form" id="ahi-task-edit-form" onSubmit={(e) => {
                                e.preventDefault();
                                let datas = ''
                                taskNameAlert = false; taskDescAlert = false;

                                if ( taskNameInput.value != '' && trim( taskNameInput.value) == '') {
                                    console.log(" taskNameAlert is working");
                                     taskNameAlert = true;
                                    this.setState({ isEditDialogOpen: true, isShowPage: false, isDialogOpen: false });
                                }
                                else if (descInput.value != '' && trim(descInput.value) == '') {
                                    console.log(" taskDescAlert is working");
                                     taskDescAlert = true;
                                    this.setState({ isEditDialogOpen: true, isShowPage: false, isDialogOpen: false });
                                }
                                else {
                                     taskNameInput.value = trim( taskNameInput.value);
                                    descInput.value = trim(descInput.value);
                                    
                                    if (taskNameInput.value == '' && descInput.value != '') {
                                        datas = { taskId: this.editData.taskId, taskName: this.editData.taskName, taskDescription: descInput.value, userId: this.editData.userId }
                                    }
                                    else if (descInput.value == '' && taskNameInput.value != '') {
                                        datas = { taskId: this.editData.taskId, taskName: taskNameInput.value, taskDescription: this.editData.taskDescription, userId: this.editData.userId }
                                    }
                                    else if (descInput.value == '' && taskNameInput.value == '') {
                                        datas = { taskId: this.editData.taskId, taskName: this.editData.taskName, taskDescription: this.editData.taskDescription, userId: this.editData.userId }
                                    }
                                    else {
                                        datas = { taskId: this.editData.taskId, taskName: taskNameInput.value, taskDescription: descInput.value, userId: this.editData.userId }
                                    }
    
                                    editSubmit(datas);
                                    window.location.reload();
                                }
                                
                            }
                            }>
                                <FormGroup >
                                    {errorMessage ? (<Alert bsStyle="danger"><strong>Error!</strong> {errorMessage}</Alert>) : null}
                                </FormGroup>

                                <FormGroup >
                                    {isFetching ? (<Alert bsStyle="success" >Your Task is successfully Editted!</Alert>) : null}
                                </FormGroup>
                                <FormGroup >
                                    {taskNameAlert ? (<Alert bsStyle="danger" >Please Enter valid task name!</Alert>) : null}
                                </FormGroup>
                                <FormGroup >
                                    {taskDescAlert ? (<Alert bsStyle="danger" >Please Enter valid task description!</Alert>) : null}
                                </FormGroup>


                                <FormGroup controlId="formControlsSelect" className="ahi- task-name">
                                    <ControlLabel>Task Name</ControlLabel>
                                    <FormControl type="string" placeholder={this.editData.taskName}
                                        inputRef={(ref) => {
                                            taskNameInput = ref
                                        }}
                                    />
                                </FormGroup>

                                <FormGroup className="ahi- task-desc">
                                    <ControlLabel>Description</ControlLabel>
                                    <FormControl type="string" placeholder={this.editData.taskDescription}
                                        inputRef={(ref) => {
                                            descInput = ref
                                        }}
                                    />
                                </FormGroup>
                                <br />

                                <FormGroup>
                                    <button className="btn btn-primary canceldisplay" onClick={this.handleClose} >Cancel</button>
                                    <button type="submit" className="btn btn-success  resetdisplay" >Submit</button>
                                    <button type="button" className="btn btn-info  resetdisplay" onClick={this.editReset} >Reset</button>
                                </FormGroup>
                            </Form>

                        </div >
                    }
                    {/* ----------------------------------------------------------------------------------------------------------------------------------- */}
                </div>
                {taskData != null &&
                    this.state.isShowPage &&
                    <Table responsive bordered condensed hover className="tableStyle">
                        <thead>
                            {
                                <tr>
                                    <th>Select</th>
                                    <th><ControlLabel>TaskName</ControlLabel></th>
                                    <th><ControlLabel>Description</ControlLabel></th>
                                </tr>
                            }

                        </thead>
                        <tbody>
                            {taskData.map((row) => {
                                // console.log("row_id:" + row.taskId);
                                return <tr >
                                    <td><Checkbox name={row.taskId} onChange={this.handleInputChange}></Checkbox></td>
                                    <td><a onClick={() => this.openEditDialog(row)} title="Edit your task">{row.taskName}</a></td>
                                    <td>{row.taskDescription}</td>
                                </tr>
                            })
                            }
                        </tbody>
                    </Table>

                }
                <br />
            </div>

        );
    }
}
const mapStateToProps = state => {
    return getPropsMap(state, 'tasks');
}
const TasksHome = connect(mapStateToProps, { getData: getAllData, addSubmit: tasksSubmit, editSubmit: editSubmit, delData: deleteTask })(Tasks);
export default TasksHome;

