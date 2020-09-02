import React, { Component } from 'react'
import './Dropzone.css'

class Dropzone extends Component {
    constructor(props){
        super(props)
        this.state = {highlight :false}
        this.fileInputRef = React.createRef()
        this.openFileDialog = this.openFileDialog.bind(this)
        this.onFilesAdded = this.onFilesAdded.bind(this)
        this.onDragOver = this.onDragOver.bind(this)
        this.onDragLeave = this.onDragLeave.bind(this)
        this.onDrop = this.onDrop.bind(this)
    }

    render() {
        return (
            <div
                className={`Dropzone ${this.state.highlight ? "Highlight" : ""}`}
                onDragOver={this.onDragOver}
                onDragLeave={this.onDragLeave}
                onClick={this.openFileDialog}
                sytle={{cursor:this.props.disabled ? "default" : "pointer"}}
            >
                <input
                    ref={this.fileInputRef}
                    className="FileInput"
                    onChange={this.onFilesAdded}
                />

                <img
                    alt="upload"
                    title="upload"
                    className="Icon"
                    src="base-line-cloud_upload-24px.svg"
                />
                
                <span>Upload Files</span>
            </div>
        );
    }

    openFileDialog() {
        if (this.props.disabled) {
            return
        }

        this.fileInputRef.current.click()
    }

    onFilesAdded(evt) {
        if (this.props.disabled){
            return
        }
        const files = evt.target.files;
        if (this.props.onFilesAdded){
            this.fileListToArray(files)

            this.props.onFilesAdded(array)
        }
    }

    fileListToArray(list) {
        const array = []
        for (var i = 0; i < list.length; i++){
            array.push(list.item(i))
        }
        return array
    }

    onDragOver(evt) {
        evt.preventDefault()

        if (this.props.disabled) {
            return;
        }

        this.setState({ highlight:true})
    }

    onDragLeave() {
        this.setState({highlight:false})
    }

    onDrop(event) {
        event.preventDefault()

        if (this.props.disabled){
            return
        }

        const files = event.dataTransfer.files
        if (this.props.onFilesAdded) {
            const array = this.fileListToArray(files)
            this.props.onFilesAdded(array)
        }

        this.setState({highlight:false})
    }
}

export default Dropzone