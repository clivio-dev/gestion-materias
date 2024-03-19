import PropTypes from 'prop-types'

const FormField = ({ label, input, children }) => {
  return (
    <div className="form__field">
      {
        label && input
          ?
          <>
            <label className="form__field__label" htmlFor={label.htmlFor}>{label.text}</label>
            <input
              className="form__field__input"
              id={label.htmlFor}
              type={input.type}
              placeholder={label.text} 
              autoComplete={input.autoComplete ?? undefined}
              required={input.required}
            />
          </>
          : <></>
      }
      {children}
    </div>
  )
}

FormField.propTypes = {
  label: PropTypes.shape({ htmlFor: PropTypes.string, text: PropTypes.string }),
  input: PropTypes.shape({ type: PropTypes.string, autoComplete: PropTypes.string, required: PropTypes.bool })
}

export default FormField