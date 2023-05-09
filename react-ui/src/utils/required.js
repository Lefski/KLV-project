export const required = (value) => {
    if (!value) {
        return (
            <div className='invalid-feedback d-block'>
                This field is required!
            </div>
        );
    }
};
