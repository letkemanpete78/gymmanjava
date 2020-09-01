import React from 'react'
/*
    Utility.submitButtons(isEditable, exercise.uuid, saveMethod, updateMethod, deleteMethod)
*/

export const Utility = {
    submitButtons : (isEditable, uuid, saveMethod, updateMethod, deleteMethod) => {
        if (isEditable) {
            if (uuid !== '') {
                return (
                    <div class="submit-buttons">
                        <p>
                            <button
                                class="button-save"
                                onClick={saveMethod()}
                            >
                                Save
                            </button>
                            <button
                                class="button-cancel"
                                // onClick={}
                            >
                                Cancel
                            </button>
                        </p>
                    </div>
                )
            } else {
                return (
                    <div class="submit-buttons">
                        <p>
                            <button
                                class="button-update"
                                onClick={updateMethod()}
                            >
                                Update
                            </button>
                            <button
                                class="button-delete"
                                onClick={deleteMethod()}
                            >
                                Delete
                        </button>
                        </p>
                    </div>
                )
            }
        }
    }
}